import { Routes, RouterModule } from "@angular/router";

import { RegistrationComponent } from "./registration";
import {LoginComponent} from "./login";

const authenticationRoutes: Routes = <Routes>[
    { path: "registration", component: RegistrationComponent},
    { path: "login", component: LoginComponent},
];

export const authenticationRouting = RouterModule.forChild(authenticationRoutes);