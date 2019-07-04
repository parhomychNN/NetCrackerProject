import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomeInterfaceComponent } from './welcome-interface.component';

describe('WelcomeInterfaceComponent', () => {
  let component: WelcomeInterfaceComponent;
  let fixture: ComponentFixture<WelcomeInterfaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomeInterfaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomeInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
