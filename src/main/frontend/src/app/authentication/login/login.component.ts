import {Component} from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";

import { AuthenticationService } from "../authentication.service";

@Component({
    selector: "l2f-login",
    templateUrl: "login.component.html"
})
export class LoginComponent {
    public loginForm: FormGroup;

    constructor(private authenticationService: AuthenticationService) {
        this.buildForm();
    }

    public loginUser() {
        if (this.loginForm.valid) {
            this.authenticationService.login(
                this.loginForm.controls["username"].value,
                this.loginForm.controls["password"].value
            );
        }
    }

    private buildForm() {
        this.loginForm = new FormGroup({
            username: new FormControl(null, Validators.required),
            password: new FormControl(null, Validators.required)
        })
    }
}