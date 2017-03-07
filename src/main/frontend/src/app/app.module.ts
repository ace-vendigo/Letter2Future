import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { HttpModule } from "@angular/http";
import { ReactiveFormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
import { appRoutes } from "./app.routing";
import { SharedModule } from "./shared/index";
import { AuthenticationModule } from "./authentication/index";
import { HomeModule } from "./home/index";
import { LettersModule } from "./letters/index";
import { RouterModule } from "@angular/router";

const declarations = [
    AppComponent,
];

@NgModule({
    imports: [
        // Framework modules
        BrowserModule,
        ReactiveFormsModule,
        HttpModule,

        RouterModule.forRoot(appRoutes),

        SharedModule,

        // Feature modules
        AuthenticationModule,
        HomeModule,
        LettersModule
    ],

    declarations: declarations,

    bootstrap: declarations,

    providers: []
})
export class AppModule {
}