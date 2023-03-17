import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AdminServiceService } from '../service/admin-service.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  result:boolean = false;
  username!:string;
  password!:string;
  status!:string;
  loginSucess:boolean = false;
  constructor(private adminService:AdminServiceService, public dialogRef: MatDialogRef<LoginComponent>, private router:Router){}

  ngOnInit(): void {
  }

  onNoClick(){
          this.dialogRef.close(); 
  }

  check(): boolean {
      this.loginSucess=true;
      this.adminService.getToken(this.username,this.password).subscribe((res:any)=>{
            this.setToken(res['token']);
            this.dialogRef.close();
            this.loginSucess=false;
            this.router.navigate(['/admin']);
            console.log(res);
      },
      (error)=>{
        this.loginSucess = false;
        console.log(error);
        this.status=error['error']['message'];
        this.username='';
        this.password='';
      })
      return this.result;
  }

  setToken(Token:string){
      localStorage.setItem('token', Token);
  }

}




