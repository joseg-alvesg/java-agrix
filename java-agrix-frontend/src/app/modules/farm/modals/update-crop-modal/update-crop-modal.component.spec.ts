import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCropModalComponent } from './update-crop-modal.component';

describe('UpdateCropModalComponent', () => {
  let component: UpdateCropModalComponent;
  let fixture: ComponentFixture<UpdateCropModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateCropModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateCropModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
