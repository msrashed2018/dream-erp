import { StudyLevels } from './study-levels.enum';
import { Product } from './product.model';

export class BillProduct {
    public id : number;
    public product : Product;
    public studyLevel : StudyLevels;
    public specialPrice : number;
    public amount : number;
    public size : number;
    public notes : string;

}
