import { ClothType } from './cloth-type.model';
import { Color } from './color.model';

export class Cloth {
    public id : number;
    public clothType : ClothType;
    public color : Color;
    public amount : number;
    public registeredDate : string;
    public notes : string;
}
