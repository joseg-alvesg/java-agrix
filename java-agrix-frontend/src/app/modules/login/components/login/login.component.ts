import { Component, Injectable } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { ILogin, IRegister, IToken } from '../../interfaces/login';

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
    private router: Router,
    private loginService: LoginService,
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
    this.loginService.login(login).subscribe({
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
    this.loginService.register(login).subscribe({
      next: (response) => {
        this.message = `Usuario ${response.username} cadastrado com sucesso!`;
      },
      error: (_error) => {
        alert('Insira valores válidos!');
      },
    });
  }
}
