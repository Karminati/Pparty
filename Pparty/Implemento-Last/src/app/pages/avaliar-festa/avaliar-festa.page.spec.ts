import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AvaliarFestaPage } from './avaliar-festa.page';

describe('AvaliarFestaPage', () => {
  let component: AvaliarFestaPage;
  let fixture: ComponentFixture<AvaliarFestaPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(AvaliarFestaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
