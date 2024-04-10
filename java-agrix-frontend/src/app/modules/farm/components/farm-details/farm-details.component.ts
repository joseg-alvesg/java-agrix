import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-farm-details',
  templateUrl: './farm-details.component.html',
  styleUrl: './farm-details.component.css',
})
export class FarmDetailsComponent {
  id!: string | null;
  farm: any;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
  }

  getFarmId(): void {}
}
