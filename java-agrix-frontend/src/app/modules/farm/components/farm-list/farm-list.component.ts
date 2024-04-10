import { Component } from '@angular/core';
import { FarmService } from '../../services/farm.service';
import { FormControl, FormGroup } from '@angular/forms';
import { IFarm } from '../../interfaces/farm';

@Component({
  selector: 'app-farm-list',
  templateUrl: './farm-list.component.html',
  styleUrl: './farm-list.component.css',
})
export class FarmListComponent {
  formulario!: FormGroup;
  farms: any[] = [];
  constructor(private farmService: FarmService) {}

  ngOnInit(): void {
    this.formulario = new FormGroup({
      name: new FormControl(''),
      size: new FormControl(''),
    });

    this.getFarms();
  }

  newFarm(): void {
    const farm: IFarm = {
      name: this.formulario.value.name,
      size: this.formulario.value.size,
    };
    this.farmService.newFarm(farm).subscribe({
      next: (response) => {
        console.log('resp1', response);
        this.farms.push(response);
      },
      error: (_error) => {
        alert('Erro ao criar fazenda!');
      },
    });
  }

  getFarms(): any {
    const test = this.farmService.getFarms().subscribe({
      next: (response) => {
        this.farms = response;
      },
    });
    return test;
  }
}
