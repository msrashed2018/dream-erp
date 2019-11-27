import { School } from './school.model';
import { BillProduct } from './bill-product.model';

export class Bill {
    public id : number;
    public school: School;
    public registeredDate : string;
    public to : string;
    public notes : string;
    
}
