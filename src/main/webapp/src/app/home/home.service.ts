import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";

@Injectable()
export class HomeService {
    private newsRoute = "/news";
    private newsObservable: Observable<any>;
    
    constructor(private http: Http) {
        this.getNews();
    }
    
    public getNews() {
        if (!this.newsObservable) {
            this.newsObservable = this.http.get(this.newsRoute)
                .publishReplay(1)
                .refCount();
        }
        
        return this.newsObservable;
    }
}