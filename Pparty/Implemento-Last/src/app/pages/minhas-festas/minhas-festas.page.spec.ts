import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MinhasFestasPage } from './minhas-festas.page';

describe('MinhasFestasPage', () => {
  let component: MinhasFestasPage;
  let fixture: ComponentFixture<MinhasFestasPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(MinhasFestasPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
