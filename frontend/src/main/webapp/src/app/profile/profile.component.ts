import { Component } from "@angular/core";

import { ProfileService } from "./profile.service";
import { Profile } from "./profile";

@Component({
    selector: "profile",
    templateUrl: "profile.component.html"
})
export class ProfileComponent {
    public profile:Profile = new Profile({});

    constructor(private profileService:ProfileService) {
    }
}