import { ExpenseType } from './expense-type.model';

export class Expense {
    public id : number;
    public expenseType : ExpenseType;
    public value : number;
    public registeredDate : string;
    public notes : string;
}
