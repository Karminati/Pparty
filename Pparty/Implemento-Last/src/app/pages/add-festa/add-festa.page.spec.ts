import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AddFestaPage } from './add-festa.page';

describe('AddFestaPage', () => {
  let component: AddFestaPage;
  let fixture: ComponentFixture<AddFestaPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(AddFestaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
