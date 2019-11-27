import { ProductDetails } from './product-details.model';

export class ProductionHistory {
    public id : number;
    public productSize : ProductDetails;
    public amount : number;
    public notes : string;
    public registeredDate : string;
}
