import {Component, Injectable} from "@angular/core";
import {FormControl, FormGroup, Validators} from '@angular/forms';

import {AuthenticationService} from "../authentication.service";
import {User} from "../../shared";

@Component({
    selector: "l2f-registration",
    templateUrl: "registration.component.html"
})
@Injectable()
export class RegistrationComponent {
    private registrationForm:FormGroup;

    constructor(private authenticationService:AuthenticationService) {
        this.buildForm();

        this.registrationForm.valueChanges.subscribe(changes => {
            console.log("changes", changes);
            console.log(this.registrationForm.valid);
            console.log(this.registrationForm.errors);
        })
    }

    public submitForm() {
        if (this.registrationForm.valid) {
            let controls = this.registrationForm.controls;
            this.authenticationService.register(new User(
                controls["email"].value,
                controls["username"].value,
                controls["password"].value
            ));
        }
    }

    private buildForm() {
        this.registrationForm = new FormGroup({
            username: new FormControl(null, Validators.required),
            email: new FormControl(null, Validators.required),
            password: new FormControl(null, Validators.required),
            passwordConfirm: new FormControl(null, Validators.required)
        });
    }
}