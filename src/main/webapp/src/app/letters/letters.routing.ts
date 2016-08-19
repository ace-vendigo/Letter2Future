import { Routes, RouterModule } from "@angular/router";
import { LettersComponent } from "./letters.component";

const lettersRoutes = <Routes>[
    { path: "letters", component: LettersComponent}  
];

export const lettersRouting = RouterModule.forChild(lettersRoutes);