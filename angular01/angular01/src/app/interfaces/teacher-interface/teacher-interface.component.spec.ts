import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherInterfaceComponent } from './teacher-interface.component';

describe('TeacherInterfaceComponent', () => {
  let component: TeacherInterfaceComponent;
  let fixture: ComponentFixture<TeacherInterfaceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherInterfaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
