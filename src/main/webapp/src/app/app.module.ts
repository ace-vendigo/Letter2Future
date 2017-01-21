import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { HttpModule } from "@angular/http";
import { ReactiveFormsModule } from "@angular/forms";

// App
import { AppComponent } from './app.component';

// Routing
import { appRoutes } from "./app.routing";

// Shared
import { SharedModule } from "./shared";

// Authentication
import { AuthenticationModule } from "./authentication";

// Home
import { HomeModule } from "./home";

// Letters
import { LettersModule } from "./letters";
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