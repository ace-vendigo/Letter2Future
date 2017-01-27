import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import { Observable } from "rxjs/Rx";

import { Profile } from "./profile";
import { ProfileServiceConfig } from "./profile.service.config";

@Injectable()
export class ProfileService {
    static config = ProfileServiceConfig;
    private profile:Observable<Profile>;


    constructor(private http:Http) {
        console.log("ProfileService: constructor");
    }

    public getCurrentUser():Observable<Profile> {
        if (!this.profile) {
            this.profile = this.http.get(ProfileService.config.GET_PROFILE)
                .map((res:Response) => new Profile(res.json().principal))
                .publishReplay(1)
                .refCount();
        }
        console.log("ProfileService: getCurrentUser");
        return this.profile;
    }
}