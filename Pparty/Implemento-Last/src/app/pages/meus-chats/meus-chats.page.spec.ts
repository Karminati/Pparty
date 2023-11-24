import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MeusChatsPage } from './meus-chats.page';

describe('MeusChatsPage', () => {
  let component: MeusChatsPage;
  let fixture: ComponentFixture<MeusChatsPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(MeusChatsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
