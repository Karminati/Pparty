import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BuscarFestaPage } from './buscar-festa.page';

describe('BuscarFestaPage', () => {
  let component: BuscarFestaPage;
  let fixture: ComponentFixture<BuscarFestaPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(BuscarFestaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
