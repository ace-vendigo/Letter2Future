import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { HttpModule } from "@angular/http";
import { ReactiveFormsModule } from "@angular/forms";

// App
import { AppComponent } from './app.component';

// Routing
import { appRoutes } from "./app.routing";

// Shared
import { SharedModule } from "./shared/index";

// Authentication
import { AuthenticationModule } from "./authentication/index";

// Home
import { HomeModule } from "./home/index";

// Letters
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