import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { Subject } from "rxjs/Rx";
import { Router } from "@angular/router";
import { User } from "../shared/models/user";
import { API_PATH } from "../app.config";

@Injectable()
export class AuthenticationService {
    private authenticationActionSubject = new Subject<boolean>();
    private currentUser:User;

    public authenticationAction = this.authenticationActionSubject.asObservable();

    constructor(private http:Http,
                private router:Router) {
        this.checkCurrentUser();
    }

    private async checkCurrentUser() {
        let user:User = await this.getCurrentUser();

        if (user.getUserName()) {
            this.authenticationActionSubject.next(true);
        }
        else {
            this.authenticationActionSubject.next(false);
        }
    }


    public async getCurrentUser() {
        const GET_USER_URL = API_PATH + "user";

        if (!this.currentUser) {
            let currentUserResponse:Response = await this.http.get(GET_USER_URL).toPromise();
            try {
                let user = currentUserResponse.json().principal;

                this.currentUser = new User(user.email, user.username, null)
            }
            // Empty user response
            catch (err) {
                this.currentUser = new User();
            }
        }

        return this.currentUser;
    }

    public async register(user:User) {
        const REGISTER_USER_URL = API_PATH + "user/new";
        try {
            await this.http.post(REGISTER_USER_URL, user).toPromise();
            this.router.navigate(["/home"]);
        }
        catch (error) {
            console.log(error.json().error);
        }
    }

    public async login(username:string, password:string) {
        const LOGIN_URL = API_PATH + "login";
        try {
            await this.http.post(LOGIN_URL, {username: username, password: password}).toPromise();
            this.authenticationActionSubject.next(true);
            this.router.navigate(["/home"]);
        }
        catch (error) {
            console.log(error.json().error);
            this.authenticationActionSubject.next(false);
        }
    }

    public logout() {
        this.currentUser = null;
        this.authenticationActionSubject.next(false);
        this.router.navigate(["/home"]);
    }
}