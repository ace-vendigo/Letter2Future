import { Component, OnInit } from "@angular/core";
import { HomeService } from "./home.service";

@Component({
    selector: "home",
    templateUrl: "home.component.html"
})
export class HomeComponent implements OnInit {
    news:string;

    constructor(private homeService:HomeService) {
    }

    async ngOnInit() {
        this.news = await this.homeService.getNews();
    }
}