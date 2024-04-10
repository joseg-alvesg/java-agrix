import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');
  const Authorization = token ? JSON.parse(token).token : null;
  const request = !Authorization
    ? req
    : req.clone({ setHeaders: { Authorization } });
  return next(request);
};
