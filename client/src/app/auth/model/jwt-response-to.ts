export interface JwtResponseTO {
    token: string;
    type: string;
    username: string;
    authorities: { name: string };
}