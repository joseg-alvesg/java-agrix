import { HttpClient } from '@angular/common/http';
import { Component, Injectable } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ILogin, IRegister, IToken } from './login.interface';
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
  registro: boolean = false;
  sign: string = 'Register';
  title: string = 'Login';
  message!: string;

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
      error: (_error) => {
        alert('Usuario ou senha inválidos!');
      },
    });
  }

  formUpdate(): void {
    this.registro = !this.registro;
    this.sign = this.registro ? 'Login' : 'Register';
    this.title = this.registro ? 'Register' : 'Login';
  }

  register(): void {
    const login: IRegister = {
      username: this.formulario.value.username,
      password: this.formulario.value.password,
      role: 'USER',
    };
    this.http.post<any>('http://localhost:8080/persons', login).subscribe({
      next: (response) => {
        this.message = `Usuario ${response.username} cadastrado com sucesso!`;
      },
      error: (_error) => {
        alert('Insira valores válidos!');
      },
    });
  }
}
