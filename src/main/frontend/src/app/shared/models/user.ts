export class User {
    constructor(
        private email: string,
        private username: string,
        private password: string) {}

    public getUserName() {
        return this.username;
    }
    
    public getEmail() {
        return this.email;
    }
}