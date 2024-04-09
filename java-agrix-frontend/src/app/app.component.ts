import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'java-agrix-frontend';

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.validateToken();
  }

  private requestToken(token: any) {
    const parsedToken = token ? JSON.parse(token) : null;
    const url = 'http://localhost:8080/auth/token';
    return this.http
      .post(url, parsedToken, { responseType: 'text' })
      .subscribe({
        next: (_response) => {
          if (this.router.url === '/login') {
            this.router.navigate(['/']);
          }
        },
        error: (error) => {
          console.error(error);
        },
      });
  }

  public validateToken(): void {
    const token = localStorage.getItem('token');
    if (!token) {
      this.router.navigate(['/login']);
    }
    this.requestToken(token);
  }
}
