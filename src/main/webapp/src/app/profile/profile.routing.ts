import {Routes, RouterModule} from "@angular/router";
import {ProfileComponent} from "./profile.component";

const profileRoutes: Routes = <Routes>[
    {path: "profile", component: ProfileComponent}
];

export const profileRouting = RouterModule.forChild(profileRoutes);