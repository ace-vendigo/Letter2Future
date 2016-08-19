import {Component} from "@angular/core";
import {HomeService} from "./home.service";

@Component({
    selector: "home",
    templateUrl: "home.component.html"
})
export class HomeComponent {
    news: string;
    
    constructor(private homeService: HomeService) {
        this.homeService.getNews()
            .subscribe(news => this.news = news.json().news);
    }
}