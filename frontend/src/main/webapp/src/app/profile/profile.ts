export class Profile {
    private authorities: string[];
    private accountNonExpired: boolean;
    private accountNonLocked: boolean;
    private credentialsNonExpired: boolean;
    private enabled: boolean;
    private username: string;

    constructor (rawProfile: any) {
        this.authorities = rawProfile.authorities;
        this.accountNonExpired = rawProfile.accountNonExpired;
        this.accountNonLocked = rawProfile.accountNonLocked;
        this.credentialsNonExpired = rawProfile.credentialsNonExpired;
        this.enabled = rawProfile.enabled;
        this.username = rawProfile.username;
    }    
    
    public getUsername() {
        return this.username;
    }
}