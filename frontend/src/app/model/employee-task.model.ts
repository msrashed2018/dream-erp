import { Employee } from './employee.model';
import { Task } from './task.model';
import { Product } from './product.model';

export class EmployeeTask {
    public id : number;
    public employee : Employee;
    public task : Task;
    public product : Product;
    public size : number;
    public amount : number;
    public registeredDate : string;
    public notes : string;
}
