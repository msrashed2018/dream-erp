import { ProductDetails } from './product-details.model';

export class Production {
    public id : number;
    public productSize : ProductDetails;
    public amount : number;
    public lastUpdate : string;
    public notes : string;
}
