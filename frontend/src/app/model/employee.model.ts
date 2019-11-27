import { SalaryType } from './salary-type.enum';

export class Employee {
    public id : number;
    public name : string;
    public occupation : string;
    public salary : number;
    public salaryType : SalaryType;
    public mobileNo1 : string;
    public mobileNo2 : string;
    public address : string;
    public email : string;
    public notes : string;
}
