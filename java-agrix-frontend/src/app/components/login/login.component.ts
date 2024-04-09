import { HttpClient } from '@angular/common/http';
import { Component, Injectable } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ILogin, IToken } from './login.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
@Injectable({
  providedIn: 'root',
})
export class LoginComponent {
  private token!: IToken;
  formulario!: FormGroup;

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.formulario = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
    });
  }

  login(): void {
    const login: ILogin = {
      username: this.formulario.value.username,
      password: this.formulario.value.password,
    };
    this.http.post<any>('http://localhost:8080/auth/login', login).subscribe({
      next: (response) => {
        console.log('resp1', response);
        const token: IToken = response;
        this.token = token;
        localStorage.setItem('token', JSON.stringify(token));
        if (this.token) {
          this.router.navigate(['/']);
        }
      },
      error: (error) => {
        console.error(error);
      },
    });
    console.log('token', this.token);
  }
}
