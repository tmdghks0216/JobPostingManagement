import 'bootstrap/dist/css/bootstrap.min.css';
import './JobCard.css';

const jobs = [
  { id: 1, company: 'VACATIO', title: '[인턴] Frontend Engineer', views: 59520, condition: '경력 무관 · 학력 무관', badge: '채용시 마감', logo: 'https://via.placeholder.com/48' },
  { id: 2, company: 'VACATIO', title: '[인턴] Backend Engineer', views: 61162, condition: '경력 무관 · 학력 무관', badge: 'D-4', logo: 'https://via.placeholder.com/48' }
];

function JobCard({ job }) {
  return (
      <div className="col-md-6">
        <div className="card job-card p-3">
          <div className="d-flex align-items-center">
            <img src={job.logo} className="logo me-3" alt="company logo" />
            <div>
              <h6 className="mb-1 text-secondary">{job.company}</h6>
              <h5 className="mb-0 fw-bold">{job.title}</h5>
            </div>
            <span className="ms-auto badge badge-hot">🔥 {job.views.toLocaleString()}</span>
          </div>
          <div className="mt-2 text-muted small">{job.condition}</div>
          <div className="d-flex justify-content-between align-items-center mt-2">
            <span className="badge bg-secondary-subtle text-dark">{job.badge}</span>
            <button className="btn btn-outline-primary btn-sm">저장</button>
          </div>
        </div>
      </div>
  );
}

export default function App() {
  return (
      <div className="container my-4">
        <h3 className="fw-bold mb-4">
          전체 공고 <span className="text-muted fs-6">총 {jobs.length}건</span>
        </h3>
        <div className="row g-3">
          {jobs.map(job => (
              <JobCard key={job.id} job={job} />
          ))}
        </div>
      </div>
  );
}