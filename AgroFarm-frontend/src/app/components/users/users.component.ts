import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {
  

  user:User = new User();

  constructor(private userService:UserService,private router:Router, private snackBar: MatSnackBar){}

  newUser() {
    this.userService.createNewUser(this.user).subscribe(data => {
      console.log(data);
      // alert('Your account created sucessfully');
      this.snackBar.open('Your account created sucessfully', 'Dismiss', {
        duration: 3000
      });
      this.router.navigate(['']); 
    })
  }

}
