import { Product } from './product.model';
import { StudyLevels } from './study-levels.enum';

export class StoreSales {
    public id : number;
    public product : Product;
    public studyLevel : StudyLevels;
    public size : number;
    public amount : number;
    public specialPrice : number;
    public registeredDate : string;
    public notes : string;
}
