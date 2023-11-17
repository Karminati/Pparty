import { ComponentFixture, TestBed } from '@angular/core/testing';
import { VisualizarFestaPage } from './visualizar-festa.page';

describe('VisualizarFestaPage', () => {
  let component: VisualizarFestaPage;
  let fixture: ComponentFixture<VisualizarFestaPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(VisualizarFestaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
