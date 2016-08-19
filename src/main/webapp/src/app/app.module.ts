import { NgModule, NgModuleMetadataType } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { HttpModule } from "@angular/http";
import { ReactiveFormsModule } from "@angular/forms";

// App
import { AppComponent } from './app.component';

// Routing
import { routing, appRoutingProviders } from "./app.routing";

// Shared
import { SharedModule } from "./shared";

// Authentication
import { AuthenticationModule } from "./authentication";

// Home
import { HomeModule } from "./home";

// Letters
import { LettersModule } from "./letters";

const declarations = [
    AppComponent,
];

@NgModule(<NgModuleMetadataType>{
    imports: [
        // Framework modules
        BrowserModule,
        ReactiveFormsModule,
        HttpModule,

        routing,
        
        SharedModule,
        
        // Feature modules
        AuthenticationModule,
        HomeModule,
        LettersModule
    ],

    declarations: declarations,

    bootstrap: declarations,

    providers: [
        appRoutingProviders
    ]
})
export class AppModule {}