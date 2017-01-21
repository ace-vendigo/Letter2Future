import {Injectable} from "@angular/core";
import { Http, Response } from "@angular/http";

@Injectable()
export class HomeService {
    private newsRoute = "/news";
    private news: any;
    
    constructor(private http: Http) {
        this.getNews();
    }

    public async getNews(): Promise<any> {
        if (!this.news) {
            let newsResponse: Response = await this.http.get(this.newsRoute).toPromise();

            this.news = newsResponse.json().news;
        }

        return this.news;
    }
}