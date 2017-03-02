import { Injectable } from "@angular/core";
import { Response } from "@angular/http";

import { Subject } from "rxjs/Rx";
import { Router } from "@angular/router";
import { User } from "../shared/models/user";
import { HttpService } from "../shared/services/http.service";

@Injectable()
export class AuthenticationService {
    private authenticationActionSubject = new Subject<boolean>();
    private currentUser:User;

    public authenticationAction = this.authenticationActionSubject.asObservable();

    constructor(private httpService: HttpService,
                private router:Router) {}

    public async checkCurrentUser() {
        try {
            let user:User = await this.getCurrentUser();

            if (user.getUserName()) {
                this.authenticationActionSubject.next(true);
            }
            else {
                throw new Error("Unknown user");
            }
        } catch(error) {
            this.authenticationActionSubject.next(false);
        }
    }


    public async getCurrentUser() {
        const GET_USER_URL = "user";

        if (!this.currentUser) {
            try {
                let currentUserResponse:Response = await this.httpService.get(GET_USER_URL).toPromise();
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
        const REGISTER_USER_URL = "user/new";
        try {
            await this.httpService.post(REGISTER_USER_URL, user).toPromise();
            this.router.navigate(["/home"]);
        }
        catch (error) {
            console.log(error.json().error);
        }
    }

    public async login(username:string, password:string) {
        const LOGIN_URL = "login";

        try {
            await this.httpService.post(LOGIN_URL, {username, password}).toPromise();
            this.authenticationActionSubject.next(true);
            this.router.navigate(["/home"]);
        }
        catch (error) {
            this.authenticationActionSubject.next(false);
        }
    }

    public async logout() {
        try {
            await this.httpService.post("logout", {}).toPromise();
            this.currentUser = null;
            this.authenticationActionSubject.next(false);
        } catch (error) {
            console.log("Logout error");
        }

        this.router.navigate(["/home"]);
    }
}