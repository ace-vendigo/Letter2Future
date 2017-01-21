import {Injectable} from "@angular/core";
import { Http, Response } from "@angular/http";

import { User } from "../shared";
import {Subject} from "rxjs/Rx";
import {AuthenticationServiceConfig} from "./authentication.service.config";
import {Router} from "@angular/router";

@Injectable()
export class AuthenticationService {
    private authenticationActionSubject = new Subject<boolean>();
    private config = AuthenticationServiceConfig;
    private currentUser: User;

    public authenticationAction = this.authenticationActionSubject.asObservable();
    
    constructor (private http: Http,
    private router: Router) {
        this.checkCurrentUser();
    }

    private async checkCurrentUser() {
        let user: User = await this.getCurrentUser();

        if (user.getUserName()) {
            this.authenticationActionSubject.next(true);
        }
        else {
            this.authenticationActionSubject.next(false);
        }
    }


    public async getCurrentUser() {
        if (!this.currentUser) {
            let currentUserResponse: Response = await this.http.get(this.config.GET_PROFILE).toPromise();
            let user = currentUserResponse.json().principal;
            this.currentUser = new User(user.email, user.username, null);
        }

        return this.currentUser;
    }

    public async register (user: User) {
        try {
            await this.http.post(this.config.REGISTER_USER, user).toPromise();
            this.router.navigate(["/home"]);
        }
        catch (error) {
            console.log(error.json().error);
        }
    }
    
    public async login (username: string, password: string) {
        try {
            await this.http.post("user", {username: username, password: password}).toPromise();
            this.authenticationActionSubject.next(true);
            this.router.navigate(["/home"]);
        }
        catch (error) {
            console.log(error.json().error);
            this.authenticationActionSubject.next(false);
        }
    }
    
    public logout () {
        this.currentUser = null;
        this.authenticationActionSubject.next(false);
        this.router.navigate(["/home"]);
    }
}