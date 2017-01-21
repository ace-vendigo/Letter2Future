import { Routes } from "@angular/router";

import { RegistrationComponent } from "./registration";
import {LoginComponent} from "./login";

export const authenticationRoutes: Routes = [
    { path: "registration", component: RegistrationComponent},
    { path: "login", component: LoginComponent},
];