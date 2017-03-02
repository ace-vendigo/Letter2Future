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
}