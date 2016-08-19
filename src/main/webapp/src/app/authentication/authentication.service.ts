import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";

import { User } from "../shared";
import {Subject, Observable} from "rxjs/Rx";
import {AuthenticationServiceConfig} from "./authentication.service.config";
import {Router} from "@angular/router";

@Injectable()
export class AuthenticationService {
    private authenticationActionSubject = new Subject<boolean>();
    private config = AuthenticationServiceConfig;
    private currentUser: Observable<User>;

    public authenticationAction = this.authenticationActionSubject.asObservable();
    
    constructor (private http: Http,
    private router: Router) {
        this.getCurrentUser()
            .subscribe((user: User) => {
            if (user.getUserName()) {
                this.authenticationActionSubject.next(true);
            }
            else {
                this.authenticationActionSubject.next(false);
            }
        });
    }

    public getCurrentUser() {
        if (!this.currentUser) {
            this.currentUser = this.http.get(this.config.GET_PROFILE)
                .map(userObject => {
                    let user = userObject.json().principal;
                    return new User(user.email, user.username, null);
                })
                .publishReplay(1)
                .refCount()
        }

        return this.currentUser;
    }
    
    public register (user: User) {
        return this.http.post(this.config.REGISTER_USER, user);
    }
    
    public login (username: string, password: string) {
        var headers = "Basic " + btoa(username + ":" + password);
        this.http.post("user", {username: username, password: password})
            .subscribe((response: Response) => {
                this.authenticationActionSubject.next(true);
                this.router.navigate(["/home"]);
            });
    }
    
    public logout () {
        this.currentUser = null;
        this.authenticationActionSubject.next(false);
    }
}