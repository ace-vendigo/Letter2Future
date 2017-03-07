export const API_PATH = "/api/";
export function apiUrl(path: string) {
    return API_PATH+"/"+path;
}