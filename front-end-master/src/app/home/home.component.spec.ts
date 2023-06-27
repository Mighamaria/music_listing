import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomeComponent]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should start carousel on initialization', () => {
    spyOn(component, 'startCarousel');
    component.ngOnInit();
    expect(component.startCarousel).toHaveBeenCalled();
  });

  it('should increment slide index on nextSlide', () => {
    const initialSlideIndex = component.slideIndex;
    component.nextSlide();
    expect(component.slideIndex).toEqual((initialSlideIndex + 1) % component.newReleasedSongs.length);
  });

  it('should decrement slide index on previousSlide', () => {
    const initialSlideIndex = component.slideIndex;
    component.previousSlide();
    expect(component.slideIndex).toEqual((initialSlideIndex - 1 + component.newReleasedSongs.length) % component.newReleasedSongs.length);
  });

  it('should go to specific slide index', () => {
    const newIndex = 3;
    component.goToSlide(newIndex);
    expect(component.slideIndex).toEqual(newIndex);
  });

  it('should reset slide index on reaching last slide in nextSlide', () => {
    component.slideIndex = component.newReleasedSongs.length - 1;
    component.nextSlide();
    expect(component.slideIndex).toEqual(0);
  });

  it('should reset slide index on reaching first slide in previousSlide', () => {
    component.slideIndex = 0;
    component.previousSlide();
    expect(component.slideIndex).toEqual(component.newReleasedSongs.length - 1);
  });
});
