import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FestasSalvasPage } from './festas-salvas.page';

describe('FestasSalvasPage', () => {
  let component: FestasSalvasPage;
  let fixture: ComponentFixture<FestasSalvasPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(FestasSalvasPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
