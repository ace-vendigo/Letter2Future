import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';
import { AppModule  } from './app';

if (process.env.ENV === 'production') {
    enableProdMode();
}

//noinspection TypeScriptValidateTypes
platformBrowserDynamic().bootstrapModule(AppModule);