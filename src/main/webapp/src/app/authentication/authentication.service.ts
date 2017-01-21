import {Injectable} from "@angular/core";
import {Http} from "@angular/http";

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
        return this.http.post(this.config.REGISTER_USER, user)
            .map((response) => {
                this.router.navigate(["/home"]);
                return response;
            });
    }
    
    public login (username: string, password: string) {
        this.http.post("user", {username: username, password: password})
            .subscribe(() => {
                this.authenticationActionSubject.next(true);
                this.router.navigate(["/home"]);
            });
    }
    
    public logout () {
        this.currentUser = null;
        this.authenticationActionSubject.next(false);
        this.router.navigate(["/home"]);
    }
}